# self: 자기 자신을 참조, 메모리 주소를 참조
class Puppy:
    def __init__(self):
        self.name = "Ruffus"
        self.breed = "Beagle"
        print("Puppy is born!")

# ruffus가 Puppy의 한 종류
ruffus = Puppy()

print(ruffus)
