function variance = varianceDRV(x, Px)
mu = x'*Px;
variance = (x - mu).^2'*Px;
