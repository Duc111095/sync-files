#!/bin/bash

ENV_LIST=/home/fbo/syncfilekafka/sync-files/scripts/env_list.txt

while IFS= read -r line; do

  [ -z "$line" ] && continue
  [[ "$line" =~ ^# ]] && continue

  eval $line
  generate_post_data() {
    cat <<EOF
    { "name": "$ma_bp-sysfileoutbox-connector",
    "config": {
        "connector.class": "io.debezium.connector.sqlserver.SqlServerConnector",
        "database.hostname": "$n_server",
        "database.instance": "SQL2014",
        "database.user": "sa",
        "database.password": "123456a@@",
        "database.names": "$n_database",
        "topic.prefix": "$ma_bp-sysfileoutbox",
        "table.include.list": "dbo.sysfileoutbox",
        "schema.history.internal.kafka.bootstrap.servers": "localhost:9092",
        "schema.history.internal.kafka.topic": "schemahistory.$ma_bp-sysfileoutbox",
        "database.encrypt": "false"
    }
  } 	
EOF
  }
  curl -X POST localhost:8083/connectors -H 'Content-Type: application/json' -d "$(generate_post_data)"
  echo "--"
done <"$ENV_LIST"
