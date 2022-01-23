#Environment Variables

CONFIG_REPOSITORY_URI=C:/Users/rodri/IdeaProjects/webflux/config-values/

#Vault and Consul containers commands
docker run -p 8200:8200 -d --name=dev-vault --cap-add=IPC_LOCK -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' vault

docker run -p 8500:8500 -d --name=dev-consul -e CONSUL_BIND_INTERFACE=eth0 consul