import stdio
import stdrandom

rank = stdrandom.uniformInt(2, 15)
suit = stdrandom.uniformInt(1, 5)

rankArr = ["2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"]
suitsArr = ["Clubs", "Diamonds", "Hearts", "Spades"]

rankStr = ""
suitStr = ""

if rank == 2:
    rankStr = rankArr[0]
elif rank == 3:
    rankStr = rankArr[1]
elif rank == 4:
    rankStr = rankArr[2]
elif rank == 5:
    rankStr = rankArr[3]
elif rank == 6:
    rankStr = rankArr[4]
elif rank == 7:
    rankStr = rankArr[5]
elif rank == 8:
    rankStr = rankArr[6]
elif rank == 9:
    rankStr = rankArr[7]
elif rank == 10:
    rankStr = rankArr[8]
elif rank == 11:
    rankStr = rankArr[9]
elif rank == 12:
    rankStr = rankArr[10]
elif rank == 13:
    rankStr = rankArr[11]
elif rank == 14:
    rankStr = rankArr[12]
else:
    stdio.writeln("Rank Error")

if suit == 1:
    suitStr = suitsArr[0]
elif suit == 2:
    suitStr = suitsArr[1]
elif suit == 3:
    suitStr = suitsArr[2]
elif suit == 4:
    suitStr = suitsArr[3]
else:
    stdio.writeln("Suit Error")

stdio.writeln(rankStr + " of " + suitStr)