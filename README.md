# API Review ML

This project represents the back-end api responsible for recording movie reviews and indexing in
elasticsearch. During indexing a training model will infer whether the review is positive or
negative

## Technologies

* SpringBooot
* Java 11
* Elasticsearch 8+

## Pre-requisites

### Run docker-compose

````
docker-compose up -d
````

### Create processor

````
PUT _ingest/pipeline/review_classification_pipeline
{
  "description": "create classification review text",
  "processors": [
    {
      "inference": {
        "model_id": "distilbert-base-uncased-finetuned-sst-2-english",
        "target_field": "review_prediction_value",
        "field_map": {
          "text_review": "text_field"
        }
      }
    }
  ]
}
````

## Endpoints

GET All

````
curl --location --request GET 'http://localhost:8080/api/reviews/all'
````

POST

````
curl --location --request POST 'http://localhost:8080/api/reviews/doc' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "xtop",
"reviewText": "DC delivers again. I loved Wonder Woman, Aquaman as well as Zack Snyder'\''s Justice League. "
}'
````

## APP Reviews

For more information about APP [check here](https://github.com/andreluiz1987/app-reviews-ml)

## More information

For more information visit
post [Text Classification Reviews IMDB — Elasticsearch](https://medium.com/@andre.luiz1987/text-classification-reviews-imdb-elasticsearch-e4860e853d84)
