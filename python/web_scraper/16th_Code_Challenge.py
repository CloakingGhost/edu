class Player:
    def __init__(self, name, team):
        self.name = name
        self.xp = 1500
        self.team = team

    def introduce(self):
        print(f'Hello! I\'m {self.name} and I play for {self.team}')

class Team:

    def __init__(self, team_name):
        self.team_name = team_name
        self.players = []

    def show_players(self):
        for player in self.players:
            player.introduce()

    def show_xp(self):
        result = 0
        for player in self.players:
            result += player.xp
        return result
        
            
    def add_player(self, name):
        new_player = Player(name, self.team_name)
        self.players.append(new_player)

    def remove_player(self, name):
        self.players.remove(name)

        


##ghost = Player(
##    name = 'Ghost',
##    team = 'Team X',
##    )
##
##lynn = Player(
##    name = 'Lynn',
##    team = 'Tema B',
##    )
##
##ghost.introduce()
##lynn.introduce()

a = []

team_x = Team("Team X")
team_b = Team("Team B")

team_x.add_player('Ghost')
team_x.add_player('Tom')
team_b.add_player('Lynn')

team_b.show_players()
xp = team_x.show_xp()
print(xp)
        
