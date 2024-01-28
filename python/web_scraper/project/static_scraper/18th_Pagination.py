import requests
from bs4 import BeautifulSoup

all_jobs = []   

##url = 'https://weworkremotely.com/categories/remote-full-stack-programming-jobs#job-listings'
def scrape_page(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.content,"html.parser",)

    jobs = soup.find('section', class_='jobs').find_all('li')[1:-1]

    for job in jobs:
##    for id in range(1, 10):
##        job = jobs[id]
        title = job.find('span', class_='title').text
        company, position, region = job.find_all('span', class_='company')
        url = job.find('div', class_ = 'tooltip').next_sibling
        if url :     
            url = url['href']

        job_data = {
            'title' :title,
            'company' : company.text,
            'position' : position.text,
            'region' : region.text,
            'url' : f'https://weworkremotely.com{url}',
        }
        all_jobs.append(job_data)

def get_pagies(url):

    response = requests.get(url)
    soup = BeautifulSoup(response.content,"html.parser",)

    buttons = soup.find('div', class_ = 'pagination').find_all('span', class_ = 'page')
    ##print(len(buttons))
    return len(buttons)

target = 'https://weworkremotely.com/remote-full-time-jobs'

total_pages = get_pagies(f'{target}?page=1')
for _ in range(total_pages):
    url = f'{target}?page={_ + 1}'
    scrape_page(url)
print(len(all_jobs))
