##if condition:
##    "write the code to run"

if 5 < 10:
    print("Correct!")


a = "ghost"

if a == "ghost":
    print("True!")


password_correct = False

if password_correct:
    print("Here is your money")
else:
    print("Wrong password")


winner = 10

if winner > 10:
    print("Winner is greater than 10")
elif winner < 10:
    print("Winner is less than 10")
else:
    print("Winner is 10")


age = input("How old are you?")

print(type(age))

age = int(age)

if age < 18:
    print("you can't drink")
elif age < 35:
    print("You drink beer!")
elif age == 60 or age == 70:
    print("Birthday party!")
else:
    print("Go ahead!")


T = True
F = False

print(
T and T, # == True,
F and T, # == False,
T and F, # == False,
F and F, # == False,

T or T, # == True,
F or T, # == True,
T or F, # == True,
F or F, # == False,
)
