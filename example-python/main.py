from fastapi import FastAPI
from py_eureka_client.eureka_client import EurekaClient

from parameters import ParametersDTO


app = FastAPI()
client = EurekaClient(eureka_server="http://localhost:8761/eureka", app_name="model-service",
                      instance_ip="127.0.0.1", instance_port=8000, eureka_basic_auth_user="user", eureka_basic_auth_password="password")


@app.on_event("startup")
async def startup_event():
    await client.start()


@app.get("/parameters")
async def parameters():
    return {
        "parameters": [
            "previous_weight", "gestational_risk", "schooling", "has_hypertension", "has_diabetes",
            "has_pelvic_sugery", "has_urinary_infection", "has_congenital_malformation",
            "has_family_twinship", "amount_gestation", "amount_abortion", "amount_deliveries",
            "amount_cesarean", "target", "age", "fist_prenatal", "time_between_pregnancies"
        ]
    }


@app.post("/predict")
async def predict(parametersDTO: ParametersDTO):
    return parametersDTO


@app.on_event("shutdown")
async def shutdown_event():
    await client.stop()
