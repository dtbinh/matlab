function B = leastsquares(points)

y = points(:,2);
X = [ones(numel(y), 1) points(:,1)];
B = (X'*X)\X'*y;

end
