function out = variance(x)

out = sum((x - mean(x)).^2)/size(x, 2);

end
