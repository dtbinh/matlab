function J = computeCost(X, y, theta)

J = 1/(2*m)*sum((X*theta - y).^2);

end
