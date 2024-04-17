from markov_model import MarkovModel
import stdio
import sys


# Entry point.
def main():
    order_k = int(sys.argv[1])
    corrupted_string = str(sys.argv[2])

    text = sys.stdin.read()
    model = MarkovModel(text, order_k)

    decoded_text = model.replace_unknown(corrupted_string)
    stdio.writeln(decoded_text)

if __name__ == '__main__':
    main()
