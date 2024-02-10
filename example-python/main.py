from fastapi import FastAPI
from py_eureka_client.eureka_client import EurekaClient

from parameters import ParametersDTO


app = FastAPI()
client = EurekaClient(eureka_server="http://localhost:8761/eureka", app_name="model-service",
                      instance_ip="127.0.0.1", instance_port=8000)


@app.on_event("startup")
async def startup_event():
    await client.start()


@app.get("/parameters")
async def parameters():
    return {"parameters": ["PARA1", "PARA2", "PARA3"]}


@app.post("/predict")
async def predict(parametersDTO: ParametersDTO):
    return parametersDTO


@app.on_event("shutdown")
async def shutdown_event():
    await client.stop()
