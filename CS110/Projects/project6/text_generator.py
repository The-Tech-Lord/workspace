from markov_model import MarkovModel
import stdio
import sys


# Entry point.
def main():
    order_k = int(sys.argv[1])      # k
    text_length = int(sys.argv[2])  # n

    text = sys.stdin.read()
    model = MarkovModel(text, order_k)

    '''
    stdio.writeln()
    
    for key in model._st['ords '].keys():
        stdio.write("|" + key + "|")
    stdio.writeln()
    '''

    rand_text = model.gen(text[:order_k], text_length)
    stdio.writeln(rand_text)


if __name__ == '__main__':
    main()
