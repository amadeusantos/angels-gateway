from pydantic import BaseModel


class ParametersDTO(BaseModel):
    peso: float
    tempo_gestacional: int
    pressao_arterial: float
