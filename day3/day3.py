import re

# reading file content
def read_file(file_path):
    with open(file_path, 'r') as file:
        content = file.read()
    return content

# part 1
# find all instances of pattern 'mul(X, Y)' and return X * Y for each instance
def find_mul(input):
    # regex to match 'mul(X, Y)' where X and Y are 1 to 3 digits
    pattern = r'mul\((\d{1,3}),\s*(\d{1,3})\)'
    
    # find all matches of the pattern
    matches = re.findall(pattern, input)
    
    # store result of multiplication of X and Y for each match
    result = [int(x) * int(y) for x, y in matches]
    
    return result

# part 2
# remove all instances of pattern of don't()....do()" from input
def filter_dont_do(input):
    pattern = r"don't\(\).*?do\(\)"
    input = re.sub(pattern, "", input)

    pattern = r"don't\(\).*"
    result = re.sub(pattern, "", input)

    # note that the patterns does not account for newline characters! 
    # puzzle input must be on one line in order for result to be correct
    return result

# main method
if __name__ == "__main__":
    FILE_PATH = 'input.txt'
    content = read_file(FILE_PATH)
    #print(content)
    filtered_content = filter_dont_do(content)
    #print(filtered_content)
    products = find_mul(filtered_content)
    
    sum = 0 # sum of all products
    for product in products:
        sum += product

    print('sum:', sum) 
