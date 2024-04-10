# tutorial: https://colab.research.google.com/github/tensorflow/examples/blob/master/lite/codelabs/digit_classifier/ml/step2_train_ml_model.ipynb#scrollTo=Yu1BOwfPzaIy

import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt
import random


def get_train_data():
    minst_dataset = keras.datasets.mnist
    (train_images, train_labels), (test_images, test_labels) = minst_dataset.load_data()

    # normalization of images
    # pixel values between 0 and 1
    train_images = train_images / 255
    test_images = train_images / 255

    return (train_images, train_labels), (test_images, test_labels)


def build_model(train_images, train_labels):
    plt.figure(figsize=(10, 10))
    for i in range(25):
        plt.subplot(5, 5, i + 1)
        plt.xticks([])
        plt.yticks([])
        plt.grid(False)
        plt.imshow(train_images[i], cmap=plt.cm.gray)
        plt.xlabel(train_labels[i])
    plt.show()

    model = keras.Sequential([
        # 28px x 28px input images
        keras.layers.InputLayer(input_shape=(28, 28)),
        keras.layers.Reshape((28, 28, 1)),
        keras.layers.Conv2D(filters=32, kernel_size=(3, 3), activation=tf.nn.relu),
        keras.layers.Conv2D(filters=64, kernel_size=(3, 3), activation=tf.nn.relu),
        keras.layers.MaxPooling2D(pool_size=(2, 2)),
        keras.layers.Dropout(0.25),
        keras.layers.Flatten(),
        keras.layers.Dense(10)
    ])

    model.compile(
        optimizer='adam',
        loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
        metrics=['accuracy']
    )

    # fit model
    model.fit(train_images, train_labels, epochs=5)
    model.summary()
    return model


def check_model_performance(model, test_images, test_labels):
    # check model performance
    test_loss, test_accuracy = model.evaluate(test_images, test_labels)
    print('Model accuracy: ' + str(test_accuracy))


def main():
    (train_images, train_labels), (test_images, test_labels) = get_train_data()

    try:
        model = keras.models.load_model('model.keras')
    except ValueError:
        model = build_model(train_images, train_labels)
        model.save('model.keras')

    check_model_performance(model, test_images, test_labels)


if __name__ == '__main__':
    main()