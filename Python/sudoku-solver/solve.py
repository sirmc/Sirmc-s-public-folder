import re
def product(a,b):
    return [x+y for x in a for y in b]
digits = '123456789'
columns = digits
rows = 'ABCDEFGHI'

squares = product(rows, columns)
# Set of Related units
units = ([product(rows, column) for column in columns] 
        + [product(row, columns) for row in rows] 
        + [product(row, column) for row in ['ABC', 'DEF', 'GHI'] for column in ('123', '456', '789')])
# List of units of square s
unitd = dict((s, [u for u in units if s in u]) for s in squares)
# List of related squares of s 
related = dict((square, set(sum(unitd[square], []))-set([square])) for square in squares)

def grid_to_dict(grid):
    # Creates a dict of the sudoku puzzle, and calls set_square() on known squares
    chars = [c for c in grid if c in digits or c == '0']
    assert len(chars) == 81
    initial_values = dict(zip(squares, chars))
    grid_dict = dict((square, digits) for square in squares)
    for square, digit in initial_values.items():
        if digit in digits and not set_square(grid_dict, square, digit):
            return False
    return grid_dict
def set_square(grid_dict, square, value):
    #Works by eliminating all but value
    not_value = grid_dict[square].replace(value, '')
    if all(del_from_square(grid_dict, square, val2) for val2 in not_value):
        return grid_dict
    else:
        # Cannot set square to value
        return False
def del_from_square(grid_dict, square, value):
    # Deltes value from square, checks related square for same value
    # if that is the only value left in the related square, set that square to value.
    if value not in grid_dict[square]:
        return grid_dict # value already deleted
    grid_dict[square] = grid_dict[square].replace(value, '')
    if len(grid_dict[square]) == 0:
        return False
    elif len(grid_dict[square]) == 1:
        value = grid_dict[square]
        # Only 1 possible value, remove value from related units 
        if not all(del_from_square(grid_dict, square2, value) for square2 in related[square]):
            # Cannot remove value from another square, this value is wrong
            return False
    for unit in unitd[square]:
        value_in = [s for s in unit if value in grid_dict[s]]
        if len(value_in) == 0:
            return False # value cannot be added in this unit -> error
        elif len(value_in) == 1:
            # One possibility to assign value 
            if not set_square(grid_dict, value_in[0], value):
                return False
    return grid_dict
        
def depth_first_search(grid_dict):
    # Using a depth-first search to find solution
    if grid_dict is False:
        return False
    if all(len(grid_dict[s]) == 1 for s in squares):
        return grid_dict # Already solved
    # Chose square with fewest possible solutions 
    n,square = min((len(grid_dict[square]), square) for square in squares if len(grid_dict[square]) > 1)
    search = [depth_first_search(set_square(grid_dict.copy(), square, value)) for value in grid_dict[square]]
    # Return first vale that is not false (error)
    for item in search:
        if item:
            return item
    return False
def solve(grid):
    result = depth_first_search(grid_to_dict(grid))
    return [result[square] for square in squares]


def from_file(filename):
    #Parse a file into a list of strings, separated by sep.
    sfile = file(filename).read()
    sfile = sfile.strip()
    sfile = re.split('Grid [0-9][0-9]', sfile)
    sfile.pop(0)
    return sfile

def check(solution):
    # Checks each unit for errors, by sorting its values and comparing it to string '123456789'
    # which is the string of numbers that we want in each unit
    solution_dict = dict(zip(squares, solution))
    for unit in units:
        unit_numbers = []
        for square in unit:
            unit_numbers.append(solution_dict[square])
        assert ''.join(sorted(unit_numbers)) == digits, "Sudoku not solved"

problem_numbers = []
for grid in from_file('sudoku.txt'):
    solution = solve(grid)
    check(solution) # Error checking
    for i in range(9):
        row= ''.join(solution[9*i:9*i+9])
        print row
    #print solution
    print "\n"
    problem_numbers.append(100*int(solution[0])+10*int(solution[1])+int(solution[2]))

print problem_numbers
print 'Sum of all numbers for problem:'
print sum(problem_numbers)
