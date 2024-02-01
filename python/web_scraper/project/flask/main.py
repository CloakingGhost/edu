from flask import Flask, render_template, request, redirect, send_file
from extractors.web3 import web3
from extractors.berlin import Scraper
from file import save_to_file

app = Flask(__name__)

db = {}

@app.route("/")
def home():
    return render_template("home.html", name = "ghost")

@app.route("/search")
def search():
    keyword = request.args.get("keyword")
    if not keyword:
        return redirect('/')
    if keyword in db:
        jobs = db[keyword]
    else:
        s = Scraper()
        s.set_skills([keyword])
        berlin = s.get_jobs()
        web3_ = web3(keyword)
        jobs = berlin + web3_
        db[keyword] = jobs
    return render_template("search.html", keyword = keyword, jobs = jobs)

@app.route('/export')
def export():
    keyword = request.args.get("keyword")
    if not keyword:
        return redirect('/')
    if keyword not in db:
        return redirect(f'/search?keyword={keyword}')
    jobs = db[keyword]
    print(jobs)
    save_to_file(keyword, jobs)
    return send_file(f"{keyword}.csv", as_attachment=True)


if __name__ == "__main__":
    app.run()

