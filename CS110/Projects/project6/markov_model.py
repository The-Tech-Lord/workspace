from symboltable import SymbolTable
import stdio
import stdrandom
import sys


class MarkovModel(object):
    # Creates a Markov model of order k from the given text.
    def __init__(self, text, k):
        self._k = k
        self._st = SymbolTable()
        circ_text = text + text[:self._k]  # text + text[beginning (inclusive) to self._k (exclusive)]

        for i in range(len(circ_text) - self._k):
            kgram = circ_text[i:i + self._k]  # String of k characters
            next_char = circ_text[i + 2]

            # Checks if kgram or next_char exist in a SymbolTable dictionary
            if kgram not in self._st:
                self._st[kgram] = SymbolTable()

            if next_char not in self._st[kgram]:
                self._st[kgram][next_char] = 1
                continue

            self._st[kgram][next_char] += 1

    # Returns the order this Markov model.
    def order(self):
        return self._k

    # Returns the number of occurrences of kgram in this Markov model; and 0 if kgram is
    # nonexistent. Raises an error if kgram is not of length k.
    def kgram_freq(self, kgram):
        if self._k != len(kgram):
            raise ValueError('kgram ' + kgram + ' not of length ' + str(self._k))
        
        if kgram not in self._st:
            return 0

        # Checks how often kgram appears in self._st
        total_freq = 0
        for key in self._st[kgram].keys():
            total_freq += self._st[kgram][key]

        return total_freq

    # Returns number of times character c follows kgram in this Markov model; and 0 if kgram is
    # nonexistent or if it is not followed by c. Raises an error if kgram is not of length k.
    def char_freq(self, kgram, c):
        if self._k != len(kgram):
            raise ValueError('kgram ' + kgram + ' not of length ' + str(self._k))

        # If neither kgram or c exist in self._st, return 0
        if kgram not in self._st or c not in self._st[kgram]:
            return 0

        return self._st[kgram][c]

    # Returns a random character following kgram in this Markov model. Raises an error if kgram is
    # not of length k or if kgram is nonexistent.
    def rand(self, kgram):
        if self._k != len(kgram):
            raise ValueError('kgram ' + kgram + ' not of length ' + str(self._k))
        if kgram not in self._st:
            raise ValueError('Unknown kgram ' + kgram)

        val_sum = []
        for values in self._st[kgram].values():
            val_sum += [values]

        # First store the random number
        enum_val = stdrandom.discrete(val_sum)
        print(str(sum(val_sum)))

        # Enumerate the keys with keys() and if i is equal to the value stored in enum_val, return the key that was enumerated
        for i, key in enumerate(self._st[kgram].keys()):
            if i == enum_val:
                return key

    # Generates and returns a string of length n from this Markov model, the first k characters of
    # which is kgram.
    def gen(self, kgram, n):
        text = kgram
        for i in range(0, n - self.order()):
            text += self.rand(kgram)

            # Returns the last k chars of text by using colon operator
            kgram = text[-self.order():]

        return text

    # Replaces unknown characters (~) in corrupted with most probable characters from this Markov
    # model, and returns that string.
    def replace_unknown(self, corrupted):
        original = ''
        for i in range(len(corrupted)):
            if corrupted[i] == '~':
                kgram = corrupted[i - self.order():i]  # Store the kgram first
                biggest_valkey = None    # Holds the key with the largest value

                # Sets biggest_value to the first key in the dictionary/SymbolTable object
                # This is done as their is no practical way of doing this without the for loop
                for key in self._st[kgram].keys():
                    if biggest_valkey == None:
                        biggest_valkey = key

                        # Since using keys() returns an object with no length, we make it a list by surrounding with square brackets
                        if len([self._st[kgram].keys()]) == 1:
                            original += key
                            break
                        
                        continue
                    
                    # Since biggest_value is equal to a key in the dictionary of kgram, it can be used to get the value of that key
                    if self._st[kgram][biggest_val] < self._st[kgram][key]:
                        original += key
                        break
            else:
                original += corrupted[i]
        return original


# Given a list a, _argmax returns the index of the maximum value in a.
def _argmax(a):
    return a.index(max(a))


# Unit tests the data type [DO NOT EDIT].
def _main():
    text = sys.argv[1]
    k = int(sys.argv[2])
    model = MarkovModel(text, k)
    a = []
    while not stdio.isEmpty():
        kgram = stdio.readString()
        char = stdio.readString()
        a.append((kgram.replace('-', ' '), char.replace('-', ' ')))
    for kgram, char in a:
        if char == ' ':
            stdio.writef('freq(%s) = %s\n', kgram, model.kgram_freq(kgram))
        else:
            stdio.writef('freq(%s, %s) = %s\n', kgram, char, model.char_freq(kgram, char))
            

if __name__ == '__main__':
    _main()
