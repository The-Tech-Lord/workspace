from instream import InStream
from symboltable import SymbolTable
import stdio


# Entry point.
def main():
    # Set inStream to an input stream built from the file 'data/misspellings.txt'.
    instream = InStream("data/misspellings.txt")

    # Set lines to the list of lines read from inStream.
    lines = instream.readAllLines()

    # Set misspellings to a new symbol table object.
    misspellings = SymbolTable()

    for line in lines:
        # For each line (of the form 'misspelling correction') in lines...

        # Set tokens to the list obtained by splitting line using the split() method from str.
        tokens = line.split()

        # Insert the pair tokens[0]/tokens[1] into misspellings.
        misspellings[tokens[0]] = tokens[1]

    while not stdio.isEmpty():
        # As long as standard input is not empty...

        # Set word to a string read from standard input.
        word = stdio.readString()

        # If word exists in misspellings, then it is misspelled. So write the word and the
        # correction to standard output, separated by the string '->'.
        if word in misspellings:
            stdio.writeln(word + " -> " + misspellings[word])


if __name__ == '__main__':
    main()
