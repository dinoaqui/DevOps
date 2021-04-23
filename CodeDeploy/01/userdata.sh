#!/bin/bash

# Install Dependencies
sudo yum update
sudo yum install -y aws-cli ruby wget

# Install Docker
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -aG docker ec2-user

# Install CodeDeploy Agent
wget https://aws-codedeploy-us-east-2.s3.us-east-2.amazonaws.com/latest/install
chmod +x ./install
sudo ./install auto
sudo service codedeploy-agent start
sudo chmod 777 /etc/init.d/codedeploy-agent