FROM node:16 as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install -g npm@10.5.0
RUN npm ci
COPY . ./
RUN npm run build

FROM node:16
WORKDIR /app
COPY --from=build /app/build /app
# Installi serve: lihtne staatiliste failide server
RUN npm install -g serve
# Määra port, mida konteiner kuulab runtime'is
EXPOSE 3000
# Käivita serve, et serveerida ehitatud React rakendust
CMD ["serve", "-s", "/app", "-l", "3000"]
