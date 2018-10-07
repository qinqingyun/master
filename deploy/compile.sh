#!/usr/bin/env bash

if [ ! -z "$APP_KEY" ]; then
    echo "start replace appkey...\n"
    find . -type f -name "app.properties" -exec sed -i '1s/app.name=.*/app.name='$APP_KEY'/g' {} +
fi

mvn clean -U package -P $PLUS_TEMPLATE_ENV  -DskipTests=true