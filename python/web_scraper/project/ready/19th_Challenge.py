import requests
from bs4 import BeautifulSoup
## import pandas as pd

class Info:
    def __init__(self, title = 'anonymous', name = 'anonymous', url = 'anonymous'):
        self.title = title
        self.name = name
        self.url = url

    def __str__(self):
        return f'title : {self.title}, name : {self.name}, url = {self.url}'

class Scraper:

    def __init__(self, keywords = []):
        self.keywords = keywords
        ## self.df = pd.DataFrame()
        
    def get_response(self, keyword = ''):

        response = requests.get(f'https://remoteok.com/remote-{keyword}-jobs',
                        headers = {
                            'User-Agent' :
                            'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36'
                                      })
    
        return BeautifulSoup(response.content, 'html.parser')

    def scrap(self, keyword = ''):
        data = []
        soup = self.get_response(keyword)
        jobs = soup.find_all('td', class_ = 'company_and_position')[1:]

        for job in jobs:
            title = job.find('h2').text.strip()
            name = job.find('h3').text.strip()
            url = job.find('a')['href']

            info = Info(
                title = title,
                name = name,
                url = f'https://remoteok.com/remote-jobs{url}'
                )

            data.append(info.__str__())

        return {keyword : data}

    def scrap_all(self):
        data_all = []
        
        for keyword in self.keywords:
            data_all.append(self.scrap(keyword))
        ## self.df = pd.DataFrame(data_all)
        return data_all

##    def to_csv(self):   
##        excel_file = self.df.to_csv('C:/jobs.csv')
##        return excel_file

keywords = ['ios', 'java', 'flutter']

scraper = Scraper(keywords)
data = scraper.scrap_all()
print(data)
## scraper.to_csv()

        
