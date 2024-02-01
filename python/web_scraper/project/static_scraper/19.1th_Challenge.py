import requests
from bs4 import BeautifulSoup

class Scraper:
    def __init__(self):
        self.skills = []

    def set_skills(self, skills = None):
        # specified position
        if skills:
            self.skills = skills
            return
        
        # all position 
        response = requests.get('https://berlinstartupjobs.com/engineering',
                                headers = {
                                    'User-Agent':
                                    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36'
                                }
                            )
        soup = BeautifulSoup(response.content, 'html.parser')
        links_box = soup.find_all('ul', class_ = 'links')[3]
        skills_links = links_box.find_all('a')

        
        for link in skills_links:
            self.skills.append(link.text.split(' ')[0].strip())


    def get_resource(self, skill):
        response = requests.get(f'https://berlinstartupjobs.com/skill-areas/{skill}/',
                        headers = {
                            'User-Agent':
                            'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36'
                        }
                    )

        if response.status_code >= 400:
            return False

        return BeautifulSoup(response.content, 'html.parser')

    def refine(self, data):
        link = data.select_one('h4.bjs-jlid__h > a')
        title = link.text
        company = data.select_one('a.bjs-jlid__b').text
        description = data.select_one('div.bjs-jlid__description').text

        info = {
            'link': link['href'],
            'title': title,
            'company': company,
            'description': description
        }

        return info
        
    def get_jobs(self):
        result = {}
        for skill in self.skills:
            soup = self.get_resource(skill)

            # Not Found Page
            if not soup:
                continue
            jobs = soup.find_all('div', class_= 'bjs-jlid__wrapper')

            jobs_data = []
            for job in jobs:
                jobs_data.append(self.refine(job))
            result[skill] = jobs_data

        return result


skills = [
    "python",
    "typescript",
    "javascript",
    "rust"
]

s = Scraper()
s.set_skills(skills = skills)
result = s.get_jobs()
for key, value in result.items():
    print(key, ':', len(value))
    for job in value:
        print(job)
    print()
