from bs4 import BeautifulSoup
import requests

# https://web3.career/python-jobs?page=1


def web3(keyword):
    user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36'
    response = requests.get(f'https://web3.career/{keyword}-jobs',
                            headers={
                                'User-Agent': user_agent
                            })
    soup = BeautifulSoup(response.content, "html.parser")
    rows = soup.select("tr.table_row")
    jobs = []
    for row in rows:
        link = row.select_one('div.job-title-mobile > a')
        title = link.text.strip()
        company = row.select_one('td.job-location-mobile').text.strip()

        info = {
            'link': f"https://web3.career{link['href']}",
            'title': title,
            'company': company
        }
        jobs.append(info)

    return jobs
