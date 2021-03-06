# -*- coding: utf-8 -*-
"""BPNT_Neural Network.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/13dMdRV3_Tku3UIDLjyoEYjbKkjOX9pZ4
"""

# Import Libraries
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import Adam
from IPython.display import SVG
from keras.utils.vis_utils import model_to_dot
from keras.utils.vis_utils import plot_model
from csv import reader
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split

import tensorflow as tf
import os
import keras
import pydot
import graphviz
import seaborn as sns
import pandas as pd
import numpy as np

# Read dataset train and test
filename = '/content/BPNT data klasifikasi.csv'
dataset_bpnt = pd.read_csv(filename)

# filename_train = '/content/train_BPNT.csv'
# bpnt_train = pd.read_csv(filename_train)

# filename_test = '//content/test_BPNT.csv'
# bpnt_test = pd.read_csv(filename_test)

dataset_bpnt.iloc[:, 0:7]

# bpnt_train.iloc[:, 0:7]

# bpnt_test.iloc[:, 0:7]

sns.set(style="ticks")
sns.set_palette("husl")
sns.pairplot(dataset_bpnt.iloc[:,0:7], hue="status_social_assistance")

# # Make chart bpnt_train
# sns.set(style="ticks")
# sns.set_palette("husl")
# sns.pairplot(bpnt_train.iloc[:,0:7], hue="status_social_assistance")

# # Make chart bpnt_test
# sns.set(style="ticks")
# sns.set_palette("husl")
# sns.pairplot(bpnt_test.iloc[:,0:7], hue="status_social_assistance")

# Splitting the data into training and test
# X_train = bpnt_train.iloc[:, 0:6].values # Take attribute except NIK and name
# y_train = bpnt_train.iloc[:, 6].values

# encoder = LabelEncoder()
# y_train_enc = encoder.fit_transform(y_train) # Change value y_train
# Y_train = pd.get_dummies(y_train_enc).values

# X_test = bpnt_test.iloc[:, 0:6].values # Take attribute except NIK and name
# y_test = bpnt_test.iloc[:, 6].values

# encoder = LabelEncoder()
# y_test_enc = encoder.fit_transform(y_test) # Change value y_test
# Y_test = pd.get_dummies(y_test_enc).values

X = dataset_bpnt.iloc[:,0:6].values
y = dataset_bpnt.iloc[:,6].values

encoder =  LabelEncoder()
y1 = encoder.fit_transform(y)
Y = pd.get_dummies(y1).values

X_train,X_test, y_train,y_test = train_test_split(X,Y,test_size=0.2,random_state=0)

model = Sequential()
# model.add(Dense(6,input_shape=(6,),activation='relu'))
# model.add(Dense(4,activation='relu'))
# model.add(Dense(4,activation='relu'))
# model.add(Dense(1,activation='sigmoid'))

model.add(Dense(4,input_shape=(6,),activation='relu'))
# model.add(Dense(4,activation='tanh'))
# model.add(Dense(4,activation='tanh'))
model.add(Dense(2,activation='softmax'))

model.compile(optimizer = 'Adam',loss = 'categorical_crossentropy',metrics = ['accuracy'])

model.summary()

model.fit(X_train, y_train, epochs=100)

! mkdir -p saved_model
model.save('saved_model/model')

converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()
open("converted_model.tflite", "wb").write(tflite_model)

model.save('model.h5')

plot_model(model, to_file = 'model_plot.png', show_shapes = True, show_layer_names = True)
from IPython.display import Image
Image(filename = 'model_plot.png')

from IPython.display import SVG
from keras.utils.vis_utils import model_to_dot

SVG(model_to_dot(model).create(prog = 'dot', format = 'svg'))

y_pred = model.predict(X_test)
y_test_class = np.argmax(y_test, axis = 1)
y_pred_class = np.argmax(y_pred, axis = 1)

y_pred
y_test
# print(y_pred)

X_test2 = np.array([
                    [3,4,3,4,3,4]
                  ])

y_pred2 = model.predict(X_test2)
y_pred2
# print(y_pred2)
print(np.argmax(y_pred2, axis = 1))