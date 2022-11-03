FROM node:19-alpine3.15 as builder
WORKDIR /app
COPY package*.json ./
COPY yarn.lock ./
COPY nginx.conf ./nginx/
COPY . .
RUN yarn install
RUN yarn run build
CMD ["node"]

FROM nginx:1.23.2-alpine 
COPY --from=builder /app/build/* /usr/share/nginx/html/
COPY --from=builder /app/nginx/nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]