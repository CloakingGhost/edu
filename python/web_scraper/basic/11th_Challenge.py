import requests

movie_ids = [
    238,
    680,
    550,
    185,
    641,
    515042,
    152532,
    120467,
    872585,
    906126,
    840430
]

base = 'https://nomad-movies.nomadcoders.workers.dev/movies'

for id in movie_ids:
    site = f'{base}/{id}'
    response = requests.get(site)
    if response.status_code == 200:
##        convert to json
        response = response.json()
        print(f'''
title: {response['title']},
overview: {response['overview']},
vote_average: {response['vote_average']}
'''
              )
