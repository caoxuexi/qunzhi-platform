import jieba.posseg as pseg
import json
import sys


def main(text):
    # 获取字符串
    # with open('D:\\documents\\python\\fenjie\\需求分解.txt', 'r', encoding='utf-8') as file:
    #     for line in file:
    #         text += line
    # 词性划分
    list_all = []
    for p in pseg.cut(text):
        p = list(p)
        list_all.append(p)
    # print(list_all)
    # 抽取关键词
    str = ''
    list_ex = []
    for i in range(len(list_all)):
        x = list_all[i]
        if "c" in x:
            x[0] = ','
            list_ex.append(x)
        if 'n' in x or 'x' in x or 'l' in x or 'vn' in x:
            list_ex.append(x)
        if i < len(list_all) - 1:
            y = list_all[i + 1]
            if 'n' in x and 'v' in y:
                list_ex.append(y)
            if 'vn' in x and 'v' in y:
                list_ex.append(y)
    for p in list_ex:
        str += p[0].replace("\n", ",").replace("。", ",").replace("，", ",").replace("、", ",")
    # print(str)
    list_test = str.split(',')
    # 倒序删除“，”
    key = {}
    for i in range(len(list_test) - 1, -1, -1):
        keyword = ''
        if list_test[i] == '':
            list_test.remove('')
        else:
            keyword = list_test[i]
            key = {'key': keyword}
            list_test[i] = key
    data = json.dumps(list_test, ensure_ascii=False)
    print(data)


if __name__ == '__main__':
    strings = ''
    for i in range(1, len(sys.argv)):
        strings += sys.argv[i]
    main(strings)
