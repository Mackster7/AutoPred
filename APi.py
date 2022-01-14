from flask import Flask,render_template,request,redirect,jsonify
import pickle
import pandas as pd
import numpy as np


model=pickle.load(open('LinearRegressionModel.pkl','rb'))

app =Flask(__name__)

@app.route('/')
def home():
    return"Hello World"

@app.route('/predict',methods=['POST'])
def predict():
    name= request.form.get('name')
    company= request.form.get('company')
    year = request.form.get('year')
    fuel_type= request.form.get('fuel_type')
    kms_driven= request.form.get('kms_driven')

    input_query= pd.DataFrame(columns=['name', 'company', 'year', 'kms_driven', 'fuel_type'],
                                            data=np.array([name, company, year, kms_driven,fuel_type]).reshape(1, 5))

    result=model.predict(input_query)[0]

    return jsonify({'price':result})


if __name__ == '__main__':
    app.run(debug=True,host='192.168.111.210')

