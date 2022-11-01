echo "update from git"
git pull origin main

echo "Buidling app..."
yarn run build

echo "Deploying files to server..."
scp -r .\build\* tronghttse151306@34.125.151.94:/var/www/34.125.151.94/ 

echo "done"