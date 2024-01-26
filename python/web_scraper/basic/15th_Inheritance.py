class Dog:

    def __init__(self, name, breed, age):
        self.name = name
        self.age = age
        self.breed = breed

    def sleep():
        print('zzzzz')



class GuardDog(Dog):
    self.
    def __init__(self, name, breed):
        super().__init__(name, breed, 0.1)

## static
    self.aggresive = True
    
    def rrrr(self):
        print("Stay way!")

class Puppy(Dog):
    
    def __init__(self, name, breed):
        super().__init__(name, breed, 5)

## static
    self.spoiled = True
    
    def woof_woof(self):
        print('Woof Woof!')

##    def introduce(self):
##        self.woof_woof()
##        print(f'My name is {self.name} and I am a baby {self.breed}')
##        self.woof_woof()
        
 

ruffus = Puppy(
    name = "Ruffus",
    breed = "Beagle",
    )
bibi = GuardDog(
    name = "Bibi",
    breed = "Dalmatian",
    )

##bibi = Puppy(
##    name = "Bibi",
##    breed = "Dalmatian",
##    )
##ruffus.introduce()
##bibi.introduce()

ruffus.woof_woof()
bibi.rrrr()
