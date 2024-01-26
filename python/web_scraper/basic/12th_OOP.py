# OOP
# 데이터를 기반으로 동작하는 함수로 데이터를 캡슐화
##OOP는 데이터를 어떻게 구조화할지를 알려주고,
##데이터를 수정하기 위한 함수를 어디에 넣어야 하는지에 대한 안내 역할을 합니다.

ghost = {
    "name": "Ghost",
    "XP": 1000,
    "team": "Team X",
}

def introduct_player(player):
    name = player["name"]
    team = player["team"]
    print(f'Hello! My name is {name} anmd I play for {team}')

introduct_player(ghost)
