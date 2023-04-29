import sys

def seperator(string, length):
    return [ ('0x' + string[i : i + length]) for i in range(0, len(string), length)]

def common(strings, length):
    perLine = 32 / length
    print('perLine: %d'.format(perLine))
    return '\n'.join(', '.join(strings[i : i + perLine]) for i in range(0, len(strings), perLine))

if __name__ == '__main__':
    url = sys.argv[1]
    length = int(sys.argv[2])

    perLine = 32 / length

    with open(url) as file:
        contents = file.readlines()

    contents = [x.strip().replace('0x', '').replace(',', '') for x in contents]
    contents = [ for x in range(0, len(contents),  ]
    
    for i in range(0, len(contents), length):
        string = ''.join(['0x', ''.join(contents[i : i + length], ", "]))
        newContents[i] = string


    newContents = [ (''.join(newContents[i : i + perLine]) + '\n') for i in range(0, len(newContents), perLine) ]

    print(contents)