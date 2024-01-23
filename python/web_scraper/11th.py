## pypi.org
from requests import get

websites = ('https://naver.com', 'httpstat.us/101','httpstat.us/200','httpstat.us/305.com','httpstat.us/random/400-499','httpstat.us/520')

results = {}

for website in websites:
    if not website.startswith('https://'):
        website = f"https://{website}"
    response = get(website)
##    print(response.status_code)
    code = response.status_code
    if code >= 500:
        results[website] = 'server error'
##        print(f'{website} is OK')
    elif code >= 400:
        results[website] = 'client error'
    elif code >= 300:
        results[website] = 'redirection'
    elif code >= 200:
        results[website] = 'successful'
    elif code >= 100:
        results[website] = 'informaional response'


print(results)
    
    
