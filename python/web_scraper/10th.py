player = {
    'name': 'ghost',
    'age': 12,
    'alive': True,
    'fav_food': ["🥨", "🍟"]
}
print(player)

print(player.get('age'))
print(player['fav_food'])
player.pop('age')
print(player)

player['xp'] = 1500
print(player)

player['fav_food'].append('🍱')
print(player['fav_food'])
