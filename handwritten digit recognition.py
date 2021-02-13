import numpy as np
import mnist 
import matplotlib.pyplot as plt
import keras
import keras.layers as Dense
import keras.utils as to_categorical
import os
os.environ['TF_CPP_MIN_LOG_LEVEL']='3'
from PIL import Image as im 


# Load The Dataset 
train_images = mnist.train_images()
train_labels = mnist.train_labels()
test_images = mnist.test_images()
test_labels = mnist.test_labels()





# Reshape The Vectors 
train_images = train_images.reshape(60000, 784);
test_images = test_images.reshape(10000, 784);
train_images = train_images.astype('float32')
test_images = test_images.astype('float32')



# Normolize The Images 
train_images = (train_images/255)
test_images = (test_images/255) 

# building a linear stack of layers with the sequential model
model = keras.models.Sequential()
model.add(keras.layers.Dense(64, activation = 'relu' ,input_dim=784))
model.add(keras.layers.Dense(64, activation = 'relu' ))
model.add(keras.layers.Dense(10, activation = 'softmax' ))

model.compile(loss='categorical_crossentropy', metrics=['accuracy'], optimizer='adam')

model.fit(train_images,keras.utils.to_categorical(train_labels),epochs=40,batch_size=128)

# saving the model
save_dir = "C:/Users/Imad/Desktop/CF/Py"
model_name = 'best_model.h5'
model_path = os.path.join(save_dir, model_name)
model.save(model_path)
print('Saved trained model at %s ' % model_path)



test_images = mnist.test_images()
test_labels = mnist.test_labels()

test_images = test_images.reshape(10000, 784);
test_images = test_images.astype('float32')
test_images = (test_images/255) 

model = keras.models.load_model('C:/Users/Imad/Desktop/CF/Py/keras_mnist.h5')
pridictions = model.predict(test_images[:10])
print(np.argmax(pridictions,axis=1))
print(test_labels[:10])

weights = model.layers[0].get_weights()[0]
biases = model.layers[0].get_weights()[1]

arr = model.layers[0] ;

print(weights)
print(biases)
print(type(arr))


