import requests
from bs4 import BeautifulSoup
url = 'https://weworkremotely.com/categories/remote-full-stack-programming-jobs#job-listings'

response = requests.get(url)

soup = BeautifulSoup(response.content,"html.parser",)


##jobs = soup.find('section', id='category-2')
## class는 예약어로 이미 존재하기 때문에 '_' 추가, 라이브러리에 그렇게 설계 됨
## slicing
jobs = soup.find('section', class_='jobs').find_all('li')[1:-1]

## 실행 시 화면 렉 걸림
##    print(jobs)

all_jobs = []

##for job in jobs:
for id in range(1, 10):
    job = jobs[id]
    title = job.find('span', class_='title').text
##    region = job.find('span', class_='region').text
    
## 배열의 원소 개수를 알고 있으면 각 원소별로 변수를 바로 지정할 수 있다.   
    company, position, region = job.find_all('span', class_='company')
##    company = company.text
##    position = position.text
##    region = region.text
##    print(f'title: {title}, company: {company}, position: {position}, region: {region}')

## url 없으면 None 리턴하여 에러 발생
    url = job.find('div', class_ = 'tooltip').next_sibling
    if url :
## 속성값(attribute) 추출 방법        
        url = url['href']

    job_data = {
        'title' :title,
        'company' : company.text,
        'position' : position.text,
        'region' : region.text,
        'url' : f'https://weworkremotely.com{url}',
    }
##    print(job_data)
    all_jobs.append(job_data)

print(all_jobs)
