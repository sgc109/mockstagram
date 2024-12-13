# Step 1: Use the official Node.js image as the base image
FROM node:20.5.1

# Step 2: Set the working directory inside the container
WORKDIR /usr/src/app

# Step 3: Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of the application code
COPY . .

# Step 6: Expose the port your application will run on
EXPOSE 8084

# Step 7: Define the command to run the application
CMD ["npm", "start"]
