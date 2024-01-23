from random import randint


print("Welcome to python Casino")
pc_choice = randint(1, 50)

playing = True

while playing:

    user_choice = int(input("Choose number: (1-100) "))

    if (user_choice == pc_choice):
        print("You won!")
        playing = False
    elif (user_choice > pc_choice):
##        print("Lower! Computer chose", pc_choice)
        print("Lower!")        
    elif (user_choice < pc_choice):
##        print("Higher! Computer chose", pc_choice)
        print("Higher!")

'''

distance = 0
while distance < 20:
    print("I'm running", distance,"km")
    distance += 1
'''
