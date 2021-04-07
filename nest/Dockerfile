FROM node:15.12.0

ENV NODE_ENV=production

WORKDIR /app

COPY package*.json ./
RUN npm install --only=production

COPY dist ./

EXPOSE 8080

CMD ["node", "main"]
