# self: 자기 자신을 참조, 메모리 주소를 참조
class Puppy:
    def __init__(self, name, breed):
        self.name = name
        self.age = 0.1
        self.breed = breed

    def __str__(self):
        return f"{self.breed} puppy, named {self.name}\n"

    
        
 

# ruffus가 Puppy의 한 종
# ruffus는 class의 instance, Object
ruffus = Puppy(
    name = "Ruffus",
    breed = "Beagle",
    )
bibi = Puppy(
    name = "Bibi",
    breed = "Dalmatian",
    )

print(
    bibi.name,
    ruffus.name,
    )

print(
    bibi,
    ruffus,
    )
