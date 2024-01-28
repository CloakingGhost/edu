import requests
from bs4 import BeautifulSoup
import csv


class Scraper:

    def __init__(self, keyword):
        self.keyword = keyword
        self.data = []
        
    def get_response(self):

        response = requests.get(f'https://remoteok.com/remote-{self.keyword}-jobs',
                        headers = {
                            'User-Agent' :
                            'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36'
                                      })
    
        return BeautifulSoup(response.content, 'html.parser')

    def scrap(self):
        
        soup = self.get_response()
        jobs = soup.find_all('td', class_ = 'company_and_position')[1:]

        for job in jobs:
            title = job.find('h2').text.strip()
            name = job.find('h3').text.strip()
            url = job.find('a')['href']

            info = {
                "title" : title,
                "company_name" : name,
                "url" : f'https://remoteok.com/remote-jobs{url}'
                }

            self.data.append(info)

        return len(self.data)

    def to_csv(self):
        file = open(f"{self.keyword}_jobs_remoteok.csv", "w")
        w = csv.writer(file)
        w.writerow(
            [
                "Title",
                "Company",
                "URL"
            ]
        )
        data = self.data
        for d in data:
            w.writerow(d.values())
        file.close()
        return "success"

keywords = ['ios', 'java', 'flutter']

for k in keywords:
    scraper = Scraper(k)
    print(scraper.scrap())
    print(scraper.to_csv())

            

