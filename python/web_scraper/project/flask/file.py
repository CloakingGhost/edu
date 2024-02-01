def save_to_file(file_name, jobs):
    file = open(f"{file_name}.csv", "w", encoding='utf-8')
    file.write("Title,Company,Link\n")

    for job in jobs:
        file.write(f"{job['title']},{job['company']},{job['link']}\n")
    file.close()