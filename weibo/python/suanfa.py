
# coding: utf-8

# In[76]:


#非线性

import tensorflow as tf  
import numpy as np  
import matplotlib.pyplot as plt  
get_ipython().run_line_magic('matplotlib', 'inline')
  
#定义神经网络中间层权值   
#x_data = np.linspace(-1, 1, 200)#生成200个随机点，范围为-1 --> 1  
#print(x_data)
#x_data = x_data.reshape((200, 1))#维度设置为(200, 1)  
#noise = np.random.normal(0, 0.02, x_data.shape)#生成干扰项  
#x_data = [37, 28, 39 ,28 ,18 ,42, 23 ,35 ,24 ,42 ,21, 40, 22 ,47 ,21 ,17 ,35, 22, 13, 44, 25 ,27 ,29, 39 ,36]
x_data = np.array([37, 28, 39 ,28 ,18 ,42, 23 ,35 ,24 ,42 ,21, 40, 22 ,47 ,21 ,17 ,35, 22, 13, 44, 25 ,27 ,29, 39 ,36])
print(x_data)
x_data = x_data.reshape((25, 1))
print(x_data.shape)
y_data = np.array([0.0442718,0.0267102,0.0282258,0.0315615,0.0243569,0.0217477,0.0319403,0.0379676,0.0265975,0.0283727,0.019423,0.0343817,0.0366737,0.0246399,0.0281211,0.0251175,0.0245358,0.0204213,0.0245134,0.0301978,0.0245696,0.0266393,0.0107527,0.0488077,0.025471])
y_data = y_data.reshape((25, 1))
#y_data = np.square(x_data) + noise  
  
#x = tf.placeholder(tf.float32, [200, 1])  
#y = tf.placeholder(tf.float32, [200, 1])  
x = tf.placeholder(tf.float32,[25,1])  
print(x)
y = tf.placeholder(tf.float32,[25,1])  
#定义神经网络中间层权值  
weights_l1 = tf.Variable(tf.random_normal([1, 10]))#10个神经元  
biases_l1 = tf.Variable(tf.zeros([1, 10]))  
wx_plust_b_l1 = tf.matmul(x, weights_l1) + biases_l1  
l1 = tf.nn.tanh(wx_plust_b_l1)#双曲正切函数作为激活函数  
  
#定义输出层  
weights_l2 = tf.Variable(tf.random_normal([10, 1]))#输出层1个神经元  
biases_l2 = tf.Variable(tf.zeros([1, 1]))#一个偏置  
wx_plust_b_l2 = tf.matmul(l1, weights_l2) + biases_l2  
prediction = tf.nn.tanh(wx_plust_b_l2)#预测结果  
  
#代价函数  
loss = tf.reduce_mean(tf.square(y - prediction))  
#使用梯度下降  
train_step = tf.train.GradientDescentOptimizer(0.1).minimize(loss)#学习率设置为0.1  
  
with tf.Session() as sess:  
    sess.run(tf.global_variables_initializer())#变量初始化，一定要做  
    for _ in range(200):  
        sess.run(train_step, feed_dict={x:x_data, y:y_data})#使用梯度下降法进行训练参数  
  
    #获得预测值  
    prediction_value = sess.run(prediction, feed_dict={x: x_data})#得到预测结果  
  
    #画图  
    plt.figure()  
    plt.scatter(x_data, y_data)#画散点图  
    plt.plot(x_data, prediction_value, 'r-', lw = 5)#画预测的实线，红色  
    plt.show() 


# In[71]:


# 一元线性


import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt
import pymysql.cursors

# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='qq5099689439',
                             db='weibodata',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
 #   with connection.cursor() as cursor:
        # Create a new record
  #      sql = "INSERT INTO `users` (`email`, `password`) VALUES (%s, %s)"
      
  #      cursor.execute(sql, ('webmaster@python.org', 'very-secret'))

    # connection is not autocommit by default. So you must commit to save
    # your changes.
  #  connection.commit()

    with connection.cursor() as cursor:
        # Read a single record
      #  sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1` WHERE `序号`!=%s"
      #  cursor.execute(sql, ('1',))
        sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1`ORDER BY `微博昵称` "
        cursor.execute(sql)
        result = cursor.fetchall()
        arr_y=[]
        for index in range(len(result)):
            arr_y.append(float(result[index]['严谨性']))
    with connection.cursor() as cursor:
        # Read a single record
      #  sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1` WHERE `序号`!=%s"
      #  cursor.execute(sql, ('1',))
        sql = "SELECT `PosEmo` FROM `wenxin`ORDER BY `ID` "
        cursor.execute(sql)
        result = cursor.fetchall()
        arr_x=[]
        for index in range(len(result)):
            arr_x.append(float(result[index]['PosEmo']))
        
    print(arr_x)
        
finally:
    connection.close()
print(arr_y)
# 随机生成1000个点，围绕在y=0.1x+0.3的直线周围
num_points = 1000
vectors_set = []
for i in range(num_points):
    x1 = np.random.normal(0.0, 0.55)
    # np.random.normal(mean,stdev,size)给出均值为mean，标准差为stdev的高斯随机数（场），当size赋值时，如：size=100，表示返回100个高斯随机数。
    y1 = x1 * 0.1 + 0.3 + np.random.normal(0.0, 0.03)
    # 后面加的高斯分布为人为噪声
    vectors_set.append([x1, y1])

# 生成一些样本
x_data = arr_x
y_data = arr_y

#x_data = [37,28,39,28,18,42,23,35,24,40,42,21,40,22,47,21,17,35,22,13,44,25,23,27,29,39,36]
#y_data = [0.0442718,0.0267102,0.0282258,0.0315615,0.0243569,0.0217477,0.0319403,0.0379676,0.0265975,0.0,0.0283727,0.019423,0.0343817,0.0366737,0.0246399,0.0281211,0.0251175,0.0245358,0.0204213,0.0245134,0.0301978,0.0245696,0.0,0.0266393,0.0107527,0.0488077,0.025471]

plt.scatter(x_data, y_data, c='r')
plt.show()


# 构造1维的w矩阵，取值是随机初始化权重参数为[-1, 1]之间的随机数
w = tf.Variable(tf.random_uniform([1], -1.0, 1.0), name='w')
# 构造1维的b矩阵，初始化为0
b = tf.Variable(tf.zeros([1]), name='b')
# 建立回归公式，经过计算得出估计值y
y = w * x_data +b

# 定义loss函数,估计值y和实际值y_data之间的均方误差作为损失
loss = tf.reduce_mean(tf.square(y - y_data), name='loss')
# 采用梯度下降法来优化参数，学习率为0.5
optimizer = tf.train.GradientDescentOptimizer(0.5)
# train相当于一个优化器，训练的过程就是最小化loss
train = optimizer.minimize(loss, name='train')


sess = tf.Session()
# 全局变量的初始化
init = tf.global_variables_initializer()
sess.run(init)

# 打印初始化的w和b
print('w = ', sess.run(w), 'b = ', sess.run(b), 'loss = ', sess.run(loss))
# 训练迭代20次
for step in range(20):
    sess.run(train)
    # 打印训练好的w和b
    print('w = ', sess.run(w), 'b = ', sess.run(b), 'loss = ', sess.run(loss))
    


# In[31]:


# 多元线性

import tensorflow as tf
import numpy as np
from sklearn import linear_model
from sklearn import preprocessing
import matplotlib.pyplot as plt
# Read x and y
x_data = np.loadtxt("D:\ex3x.dat").astype(np.float32)
y_data = np.loadtxt("D:\ex3y.dat").astype(np.float32)

# We evaluate the x and y by sklearn to get a sense of the coefficients.
reg = linear_model.LinearRegression()
reg.fit(x_data, y_data)
print (" sklearn系数: K=%s, b=%f" % (reg.coef_, reg.intercept_))

# Now we use tensorflow to get similar results.
# Before we put the x_data into tensorflow, we need to standardize it
# in order to achieve better performance in gradient descent;
# If not standardized, the convergency speed could not be tolearated.
# Reason:  If a feature has a variance that is orders of magnitude larger than others,
# it might dominate the objective function
# and make the estimator unable to learn from other features correctly as expected.

scaler = preprocessing.StandardScaler().fit_transform(x_data)
print (scaler)


# In[73]:


import numpy as np
import tensorflow as tf
from sklearn import linear_model
from sklearn import preprocessing

# Read x and y
x_data = np.loadtxt("D:\ex4x.dat").astype(np.float32)
print(x_data)
y_data = np.loadtxt("D:\ex4y.dat").astype(np.float32)
print(type(y_data))

# We evaluate the x and y by sklearn to get a sense of the coefficients.
reg = linear_model.LinearRegression()
reg.fit(x_data, y_data)

print (" sklearn系数: K=%s, b=%f" % (reg.coef_, reg.intercept_))

# Now we use tensorflow to get similar results.

# Before we put the x_data into tensorflow, we need to standardize it
# in order to achieve better performance in gradient descent;
# If not standardized, the convergency speed could not be tolearated.
# Reason:  If a feature has a variance that is orders of magnitude larger than others, 
# it might dominate the objective function 
# and make the estimator unable to learn from other features correctly as expectkgqded.
scaler = preprocessing.StandardScaler().fit(x_data)
print (scaler.mean_, scaler.scale_)
x_data_standard = scaler.transform(x_data)


W = tf.Variable(tf.zeros([2, 1]))
b = tf.Variable(tf.zeros([1, 1]))
y = tf.matmul(x_data_standard, W) + b

#loss = tf.reduce_mean(tf.reduce_sum(tf.square(y - y_data.reshape(-1, 1))))
loss = tf.reduce_mean(tf.square(y - y_data.reshape(-1, 1)))

#l1 = y - y_data.reshape(-1, 1)
#l2=tf.reduce_mean(y - y_data.reshape(-1, 1))


optimizer = tf.train.GradientDescentOptimizer(0.3)
train = optimizer.minimize(loss)

init = tf.initialize_all_variables()


sess = tf.Session()
sess.run(init)
for step in range(100):
    sess.run(train)
    if step % 10 == 0:
        print( step, sess.run(W).flatten(), sess.run(b).flatten(),sess.run(loss))
      #  l1=tf.reduce_mean(y - y_data)

print ("Coefficients of tensorflow (input should be standardized): K=%s, b=%s" % (sess.run(W).flatten(), sess.run(b).flatten()))
#print(sess.run(l1),sess.run(l2))
print ("Coefficients of tensorflow (raw input): K=%s, b=%s" % (sess.run(W).flatten() / scaler.scale_, sess.run(b).flatten() - np.dot(scaler.mean_ / scaler.scale_, sess.run(W))),"loss=",sess.run(loss))


# In[62]:


import pymysql.cursors
import numpy as np
import tensorflow as tf
from sklearn import linear_model
from sklearn import preprocessing

# Connect to the database
connection = pymysql.connect(host='localhost',
                             user='root',
                             password='qq5099689439',
                             db='weibodata',
                             charset='utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
 #   with connection.cursor() as cursor:
        # Create a new record
  #      sql = "INSERT INTO `users` (`email`, `password`) VALUES (%s, %s)"
      
  #      cursor.execute(sql, ('webmaster@python.org', 'very-secret'))

    # connection is not autocommit by default. So you must commit to save
    # your changes.
  #  connection.commit()

    with connection.cursor() as cursor:
        # Read a single record
      #  sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1` WHERE `序号`!=%s"
      #  cursor.execute(sql, ('1',))
        sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1`ORDER BY `微博昵称` "
        cursor.execute(sql)
        result = cursor.fetchall()
        arr_y=[]
        for index in range(len(result)):
            arr_y.append(float(result[index]['严谨性']))
    with connection.cursor() as cursor:
        # Read a single record
      #  sql = "SELECT `微博昵称`, `严谨性` FROM `sheet1` WHERE `序号`!=%s"
      #  cursor.execute(sql, ('1',))
        sql = "SELECT `PosEmo`, `NegEmo` FROM `wenxin`ORDER BY `ID` "
        cursor.execute(sql)
        result = cursor.fetchall()
        arr_x=[]
        for index in range(len(result)):
            arr_x.append([float(result[index]['PosEmo']),float(result[index]['NegEmo'])])
        
    print(arr_x)
        
finally:
    connection.close()
print(arr_y)
# Read x and y
#x_data = np.loadtxt("D:\ex4x.dat").astype(np.float32)
y_data = np.array(arr_y)
print(x_data)
y_data = np.array(arr_y)
print(type(y_data))

# We evaluate the x and y by sklearn to get a sense of the coefficients.
reg = linear_model.LinearRegression()
reg.fit(x_data, y_data)

print (" sklearn系数: K=%s, b=%f" % (reg.coef_, reg.intercept_))

# Now we use tensorflow to get similar results.

# Before we put the x_data into tensorflow, we need to standardize it
# in order to achieve better performance in gradient descent;
# If not standardized, the convergency speed could not be tolearated.
# Reason:  If a feature has a variance that is orders of magnitude larger than others, 
# it might dominate the objective function 
# and make the estimator unable to learn from other features correctly as expectkgqded.
scaler = preprocessing.StandardScaler().fit(x_data)
print (scaler.mean_, scaler.scale_)
x_data_standard = scaler.transform(x_data)


W = tf.Variable(tf.zeros([2, 1]))
b = tf.Variable(tf.zeros([1, 1]))
y = tf.matmul(x_data_standard, W) + b

#loss = tf.reduce_mean(tf.reduce_sum(tf.square(y - y_data.reshape(-1, 1))))
loss = tf.reduce_mean(tf.square(y - y_data.reshape(-1, 1)))/2

#l1 = y - y_data.reshape(-1, 1)
#l2=tf.reduce_mean(y - y_data.reshape(-1, 1))


optimizer = tf.train.GradientDescentOptimizer(0.3)
train = optimizer.minimize(loss)

init = tf.initialize_all_variables()


sess = tf.Session()
sess.run(init)
for step in range(100):
    sess.run(train)
    if step % 10 == 0:
        print( step, sess.run(W).flatten(), sess.run(b).flatten(),sess.run(loss))
      #  l1=tf.reduce_mean(y - y_data)

print ("Coefficients of tensorflow (input should be standardized): K=%s, b=%s" % (sess.run(W).flatten(), sess.run(b).flatten()))
#print(sess.run(l1),sess.run(l2))
print ("Coefficients of tensorflow (raw input): K=%s, b=%s" % (sess.run(W).flatten() / scaler.scale_, sess.run(b).flatten() - np.dot(scaler.mean_ / scaler.scale_, sess.run(W))),"loss=",sess.run(loss))

