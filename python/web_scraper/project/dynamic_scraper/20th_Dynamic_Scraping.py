from playwright.sync_api import sync_playwright
import time
from bs4 import BeautifulSoup
import csv

class Scraper:
    def __init__(self, keyword):
        self.keyword = keyword
        self.data = []
        
    def get_resource(self):
        p = sync_playwright().start()

        ## headless : 브라우저를 실행 시키지만 눈에 보이지 않음
        browser = p.chromium.launch(headless=False)

        page = browser.new_page()

        ##page.goto("https://www.wanted.co.kr/")
        page.goto(f"https://www.wanted.co.kr/search?query={self.keyword}&tab=position")

        time.sleep(2)

        ##page.click("button.Aside_searchButton__Xhqq3")
        ####page.locator("button.Aside_searchButton__Xhqq3").click()
        ##
        ####page.screenshot(path="screnshot.png")
        ##
        ##time.sleep(2)
        ##
        ##page.get_by_placeholder("검색어를 입력해 주세요.").fill("java")
        ##
        ##time.sleep(2)
        ##
        ##page.keyboard.down("Enter")
        ##
        ##time.sleep(2)
        ##
        ##page.click("a#search_tab_position")
        ##
        ##time.sleep(2)

        for _ in range(4):
            page.keyboard.down("End")
            time.sleep(2)

        content = page.content()

        browser.close()

        p.stop()

        return content

    def search_jobs(self):
        soup = BeautifulSoup(self.get_resource(), "html.parser")

        ## type: list
        jobs = soup.find_all("div", class_ = "JobCard_container__FqChn")


        for job in jobs:
            link = f"https://www.wanted.co.kr{job.find('a')['href']}"
            title = job.find("strong", class_="JobCard_title__ddkwM").text
            company_name = job.find("span", class_="JobCard_companyName__vZMqJ").text
            location = job.find("span", class_ = "JobCard_location__2EOr5").text
            reward = job.find("span", class_ = "JobCard_reward__sdyHn").text

            job = {
                "title": title,
                "company_name": company_name,
                "location": location,
                "reward": reward,
                "link": link,
            }
            self.data.append(job)

        return len(self.data)

    def to_csv(self):
        if not self.data:
            return "Wrong word or No searching Information"
        
        file = open(f"{self.keyword}_jobs.csv", "w")
        writter = csv.writer(file)
        writter.writerow(
            [
                "Title",
                "Company",
                "Location",
                "Reward",
                "Link",
            ]
        )

        data = self.data
        for d in data:
            writter.writerow(d.values())
        file.close()
        
        return "success"

keywords = [
    "ios",
    "java",
    "flutter",
]
for keyword in keywords:
    s = Scraper(keyword)
    print(s.search_jobs())
    print(s.to_csv())
    
