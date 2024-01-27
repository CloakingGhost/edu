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
'''
        #내가 보낸 request 객체에 접근 가능
        print(response.request)

        # 응답 코드
        print(response.status_code)

        # 200 (OK 코드)이 아닌 경우 에러 raise
        print(response.raise_for_status())

        # json response일 경우 딕셔너리 타입으로 바로 변환
        print(response.json())

        # content 속성을 통해 바이너리 타입으로 데이터를 받을 수 있다.
        print(response.content)

        # text 속성을 통해 UTF-8로 인코딩된 문자열을 받을 수 있다.
        print(response.text)

        # encoding 정보 확인
        print(response.encoding)    
'''
