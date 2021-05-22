FROM node:12

# Create Directory for the Container
WORKDIR /usr/src/app 
COPY package.json .
# Install all Packages
RUN npm install
ADD . /usr/src/app
# TypeScript
RUN npm run tsc
CMD [ "npm", "start" ]