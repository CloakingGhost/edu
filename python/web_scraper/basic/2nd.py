def say_hello(name="somebody", age=None):
    print("hello", name)
    print("you are", age, "years old")


say_hello('ghost', 30)
say_hello('mike', 16)
say_hello('lily', 18)
say_hello(False, 33)
say_hello(age=22)
