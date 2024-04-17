import stdio
import sys

# Inputs
n1_pennies = int(sys.argv[1])
n2_pennies = int(sys.argv[2])
probability = float(sys.argv[3]) # p

# Calculations
probability2 = 1 - probability   # q

player1_prob = (1 - pow(probability / probability2, n2_pennies)) / (1 - pow(probability / probability2, n1_pennies + n2_pennies))
player2_prob = (1 - pow(probability2 / probability, n1_pennies)) / (1 - pow(probability2 / probability, n1_pennies + n2_pennies))

# Outputs
stdio.writeln(str(player1_prob) + " " + str(player2_prob))
