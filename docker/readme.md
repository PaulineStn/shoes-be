sudo docker build -t shoes-app . 
sudo docker run -d -p 5432:5432 --name shoes-container shoes-app